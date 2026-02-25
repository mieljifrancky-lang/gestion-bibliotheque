// Dark/Light mode
const themeToggle = document.getElementById('theme-toggle');
const mainContent = document.getElementById('main-content');

function applyTheme(isLight) {
    const themeIcon = themeToggle ? themeToggle.querySelector('i') : null;

    if (isLight) {
        // --- Passer en Mode Clair ---
        if (mainContent) mainContent.style.backgroundColor = 'rgba(255, 255, 255, 0.85)';

        document.querySelectorAll('.content-text').forEach(el => {
            el.classList.remove('text-gray-300', 'text-gray-400');
            el.classList.add('text-gray-700');
        });

        const mainCard = document.querySelector('.main-card');
        if (mainCard) {
            mainCard.classList.remove('bg-gray-800', 'border-gray-700');
            mainCard.classList.add('bg-white', 'border-gray-200');
        }

        document.querySelectorAll('.stat-card').forEach(card => {
            card.classList.remove('bg-gray-800', 'border-gray-700');
            card.classList.add('bg-white', 'border-gray-200');
            const title = card.querySelector('h3');
            if (title) {
                title.classList.remove('text-gray-200');
                title.classList.add('text-gray-800');
            }
        });

        if (themeIcon) {
            themeIcon.setAttribute('data-feather', 'moon');
        }
    } else {
        // --- Revenir au Mode Sombre (Défaut) ---
        if (mainContent) mainContent.style.backgroundColor = 'rgba(0, 0, 0, 0.7)';

        document.querySelectorAll('.content-text').forEach(el => {
            el.classList.remove('text-gray-700');
            el.classList.add('text-gray-300');
        });

        const mainCard = document.querySelector('.main-card');
        if (mainCard) {
            mainCard.classList.remove('bg-white', 'border-gray-200');
            mainCard.classList.add('bg-gray-800', 'border-gray-700');
        }

        document.querySelectorAll('.stat-card').forEach(card => {
            card.classList.remove('bg-white', 'border-gray-200');
            card.classList.add('bg-gray-800', 'border-gray-700');
            const title = card.querySelector('h3');
            if (title) {
                title.classList.remove('text-gray-800');
                title.classList.add('text-gray-200');
            }
        });

        if (themeIcon) {
            themeIcon.setAttribute('data-feather', 'sun');
        }
    }

    if (typeof feather !== 'undefined') {
        feather.replace();
    }
}

// Initialiser le thème au chargement
document.addEventListener('DOMContentLoaded', () => {
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme === 'light') {
        applyTheme(true);
    } // Sinon sombre par défaut
});

if (themeToggle) {
    themeToggle.addEventListener('click', () => {
        let isLight = localStorage.getItem('theme') === 'light';
        isLight = !isLight; // Basculer

        applyTheme(isLight);
        localStorage.setItem('theme', isLight ? 'light' : 'dark');
    });
}

// Fonctions pour récupérer les données et initialiser les graphiques (uniquement si les canvas existent)
const livresChartCanvas = document.getElementById('livresChart');
if (livresChartCanvas) {
    Promise.all([
        fetch('/api/documents/livres').then(r => r.json()),
        fetch('/api/documents/magazines').then(r => r.json()),
        fetch('/api/emprunts').then(r => r.json())
    ]).then(([livres, magazines, emprunts]) => {

        // Calcul stats Livres
        const dispoLivres = livres.filter(l => l.disponible).length;
        const empruntesLivres = livres.length - dispoLivres;

        // Calcul stats Magazines
        const dispoMagazines = magazines.filter(m => m.disponible).length;
        const empruntesMagazines = magazines.length - dispoMagazines;

        // Calcul stats Emprunts
        const actifsEmprunts = emprunts.filter(e => !e.rendu).length;
        const terminesEmprunts = emprunts.length - actifsEmprunts;

        // Render Livres Chart
        new Chart(livresChartCanvas.getContext('2d'), {
            type: 'doughnut',
            data: {
                labels: ['Disponibles', 'Empruntés'],
                datasets: [{
                    data: [dispoLivres, empruntesLivres],
                    backgroundColor: ['#3b82f6', '#ef4444'],
                    borderWidth: 2
                }]
            },
            options: {
                responsive: true,
                plugins: { legend: { position: 'bottom' } },
                animation: { animateScale: true, animateRotate: true }
            }
        });

        // Render Magazines Chart
        const magazinesChartCanvas = document.getElementById('magazinesChart');
        if (magazinesChartCanvas) {
            new Chart(magazinesChartCanvas.getContext('2d'), {
                type: 'doughnut',
                data: {
                    labels: ['Disponibles', 'Empruntés'],
                    datasets: [{
                        data: [dispoMagazines, empruntesMagazines],
                        backgroundColor: ['#10b981', '#f59e0b'],
                        borderWidth: 2
                    }]
                },
                options: { responsive: true, plugins: { legend: { position: 'bottom' } } }
            });
        }

        // Render Emprunts Chart
        const empruntsChartCanvas = document.getElementById('empruntsChart');
        if (empruntsChartCanvas) {
            new Chart(empruntsChartCanvas.getContext('2d'), {
                type: 'doughnut',
                data: {
                    labels: ['Actifs', 'Terminés'],
                    datasets: [{
                        data: [actifsEmprunts, terminesEmprunts],
                        backgroundColor: ['#8b5cf6', '#f43f5e'],
                        borderWidth: 2
                    }]
                },
                options: { responsive: true, plugins: { legend: { position: 'bottom' } } }
            });
        }
    }).catch(err => console.error("Erreur chargement des stats dashboard:", err));
}