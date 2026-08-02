const CACHE = 'relay-v2';
// The chat widget is precached so the bubble still opens on a cold offline
// load. Shaggoth is unreachable in that state, so it answers from its own
// offline fallbacks rather than showing nothing at all.
const PRECACHE = [
  '/',
  '/manifest.json',
  '/assets/favicon.png',
  '/assets/chat-widget.css',
  '/assets/chat-widget.js'
];

self.addEventListener('install', e => {
  e.waitUntil(caches.open(CACHE).then(c => c.addAll(PRECACHE)));
  self.skipWaiting();
});

self.addEventListener('activate', e => {
  e.waitUntil(
    caches.keys().then(keys =>
      Promise.all(keys.filter(k => k !== CACHE).map(k => caches.delete(k)))
    )
  );
  self.clients.claim();
});

self.addEventListener('fetch', e => {
  if (e.request.method !== 'GET') return;
  e.respondWith(
    fetch(e.request).then(r => {
      const clone = r.clone();
      caches.open(CACHE).then(c => c.put(e.request, clone));
      return r;
    }).catch(() => caches.match(e.request))
  );
});
