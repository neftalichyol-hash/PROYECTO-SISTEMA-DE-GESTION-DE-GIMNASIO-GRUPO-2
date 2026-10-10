// Ayudas extra para los módulos 4 y 5
const hora = h => h ? String(h).slice(0, 5) : '';
const fechaHora = f => f ? fecha(f) + ' ' + (String(f).split('T')[1] || '').slice(0, 5) : '';
const dinero = n => n == null ? '' : Number(n).toFixed(2);
const opciones = (lista, valor, texto) => lista.map(x => `<option value="${x[valor]}">${esc(texto(x))}</option>`).join('');
const nombreCli = c => c.nombres + ' ' + c.apellidos;
const vacio = (n, t) => `<tr><td colspan="${n}" class="vacio">${t}</td></tr>`;
