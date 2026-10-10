const f1 = $('#solicitar'), f2 = $('#resetear');
const sinConexion = m => m.startsWith('No hay conexión');

f1.onsubmit = async e => {
  e.preventDefault();
  const boton = f1.querySelector('button'); boton.disabled = true;
  try {
    const r = await api('/auth/recuperacion/solicitar', 'POST', { correo: f1.correo.value });
    const token = (String(r).match(/[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}/i) || [''])[0];
    if (!token) throw new Error('El backend no devolvió un código reconocible: ' + r);
    $('#tokenVisible').textContent = token; f2.token.value = token;
    $('#paso1').hidden = true; $('#paso2').hidden = false;
    toast('Código generado');
  } catch (err) {
    toast(sinConexion(err.message) || !err.message.includes('error') ? err.message : 'No existe un usuario registrado con ese correo.', 'error');
  } finally { boton.disabled = false; }
};

f2.onsubmit = async e => {
  e.preventDefault();
  if (f2.nuevaContrasena.value !== f2.repetir.value) { toast('Las contraseñas no coinciden.', 'error'); return; }
  const boton = f2.querySelector('button'); boton.disabled = true;
  try {
    await api('/auth/recuperacion/resetear', 'POST', { token: f2.token.value.trim(), nuevaContrasena: f2.nuevaContrasena.value });
    toast('Contraseña cambiada. Ya puedes iniciar sesión.');
    setTimeout(() => location.href = 'index.html', 1800);
  } catch (err) {
    toast(sinConexion(err.message) ? err.message : 'El código es inválido, ya fue usado o expiró.', 'error');
    boton.disabled = false;
  }
};
