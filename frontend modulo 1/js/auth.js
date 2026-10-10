const f = $('form'), boton = f.querySelector('button');
f.onsubmit = async e => {
  e.preventDefault();
  const registro = f.id === 'registro', texto = boton.textContent;
  boton.disabled = true; boton.textContent = 'Un momento...';
  try {
    const u = await api(registro ? '/auth/register' : '/auth/login', 'POST', Object.fromEntries(new FormData(f)));
    localStorage.setItem('usuario', JSON.stringify(u));
    location.href = 'inicio.html';
  } catch (err) {
    toast(registro ? err.message : 'Usuario o contraseña incorrectos.', 'error');
    boton.disabled = false; boton.textContent = texto;
  }
};
