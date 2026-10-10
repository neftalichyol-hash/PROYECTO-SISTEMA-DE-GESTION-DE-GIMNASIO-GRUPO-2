// Utilidades compartidas por todas las pantallas
const API = 'http://localhost:8080/api';
const $ = s => document.querySelector(s);
const esc = v => String(v ?? '').replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));

async function api(ruta, metodo = 'GET', cuerpo) {
  let r;
  try {
    r = await fetch(API + ruta, { method: metodo, headers: {'Content-Type': 'application/json'}, body: cuerpo ? JSON.stringify(cuerpo) : undefined });
  } catch { throw new Error('No hay conexión con el backend. ¿Está corriendo en el puerto 8080?'); }
  const t = await r.text(); let d = null;
  try { d = t ? JSON.parse(t) : null; } catch { d = t; }
  if (!r.ok) throw new Error((d && d.message) || 'No se pudo completar la acción (error ' + r.status + ').');
  return d;
}

const sesion = () => JSON.parse(localStorage.getItem('usuario') || 'null');

function toast(mensaje, tipo = 'ok') {
  let caja = $('#toasts');
  if (!caja) { caja = document.createElement('div'); caja.id = 'toasts'; document.body.append(caja); }
  const t = document.createElement('div');
  t.className = 'toast ' + tipo; t.textContent = mensaje; caja.append(t);
  setTimeout(() => t.remove(), 3800);
}

function pill(estado) {
  const t = estado.replace(/_/g, ' ').toLowerCase();
  return `<span class="pill ${esc(estado)}">${esc(t[0].toUpperCase() + t.slice(1))}</span>`;
}

const fecha = f => f ? String(f).split('T')[0].split('-').reverse().join('/') : '';
