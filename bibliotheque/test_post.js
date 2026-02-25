const emprunt = {
    utilisateur: { id: 1 },
    document: { id: 31, type: "Livre" },
    dateEmprunt: "2026-02-22",
    dateRetour: "2026-02-23",
    rendu: false
};

fetch("http://localhost:8081/api/emprunts", {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
    },
    body: JSON.stringify(emprunt)
}).then(res => res.text().then(text => console.log(res.status, text)))
    .catch(err => console.error(err));
