const livreData = {
    type: 'Livre',
    titre: 'Sequential Test Book',
    auteur: 'Author',
    isbn: '1111',
    disponible: false
};

async function testSequential() {
    console.log("1. Creating Book...");
    const resDoc = await fetch('http://localhost:8081/api/documents/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(livreData)
    });

    if (!resDoc.ok) {
        console.error("Book Creation Failed", resDoc.status);
        return;
    }

    const savedLivre = await resDoc.json();
    console.log("Book Created successfully:", savedLivre);

    console.log("2. Creating Emprunt for Book ID:", savedLivre.id);
    const empruntData = {
        utilisateur: { id: 1 },
        document: {
            id: savedLivre.id,
            type: 'Livre' // Important for Jackson
        },
        dateEmprunt: "2026-02-22",
        dateRetour: "2026-02-23",
        rendu: false
    };

    const resEmp = await fetch('http://localhost:8081/api/emprunts', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(empruntData)
    });

    if (!resEmp.ok) {
        console.error("Emprunt Creation Failed!", resEmp.status, await resEmp.text());
        return;
    }

    const savedEmprunt = await resEmp.json();
    console.log("Emprunt Created Successfully:", savedEmprunt);
}

testSequential();
