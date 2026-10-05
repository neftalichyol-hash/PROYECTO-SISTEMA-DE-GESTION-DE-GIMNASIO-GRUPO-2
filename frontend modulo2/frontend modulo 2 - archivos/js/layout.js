// Menú lateral y barra superior. Para agregar un módulo, solo se añade un grupo a MODULOS.
const MODULOS = [
  { titulo: 'General', items: [{ id: 'inicio', texto: 'Inicio', href: 'inicio.html' }] },
  { titulo: 'Módulo 1: Administración', items: [
    { id: 'areas', texto: 'Áreas', href: 'areas.html' },
    { id: 'equipamiento', texto: 'Equipamiento', href: 'equipamiento.html' }] },
  { titulo: 'Módulo 2: Clientes y membresías', items: [
    { id: 'clientes', texto: 'Clientes', href: 'clientes.html' },
    { id: 'entrenadores', texto: 'Entrenadores', href: 'entrenadores.html' },
    { id: 'membresias', texto: 'Membresías', href: 'membresias.html' }] },
  // { titulo: 'Módulo 3: ...', items: [{ id: 'ejercicios', texto: 'Ejercicios', href: 'ejercicios.html' }] },
];

function proteger(activa, titulo) {
  const u = sesion();
  if (!u) { location.href = 'index.html'; return; }
  const nombre = u.nombreUsuario || 'Usuario';
  $('#menu').innerHTML = '<div class="marca">Gym<span>Admin</span></div>' +
    MODULOS.map(g => `<p class="grupo">${g.titulo}</p>` +
      g.items.map(i => `<a href="${i.href}" class="${i.id === activa ? 'on' : ''}">${i.texto}</a>`).join('')).join('') +
    '<button class="salir" id="salir">Cerrar sesión</button>';
  $('#topbar').innerHTML = `<button id="burger" aria-label="Menú">☰</button><h1>${esc(titulo)}</h1>
    <div class="usuario"><span class="avatar">${esc(nombre[0].toUpperCase())}</span><span><b>${esc(nombre)}</b><small>${esc(u.rol)}</small></span></div>`;
  $('#burger').onclick = () => document.body.classList.toggle('abierto');
  $('#salir').onclick = () => { localStorage.removeItem('usuario'); location.href = 'index.html'; };
}
