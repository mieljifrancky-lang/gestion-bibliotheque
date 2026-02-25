const livreData = {
    type: 'Livre',
    titre: 'Test POST from Node',
    auteur: 'Author',
    isbn: '1111',
    disponible: false
};

fetch('http://localhost:8081/api/documents/', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(livreData)
})
    .then(res => res.json())
    .then(data => {
        console.log("Document Created:", JSON.stringify(data));
    })
    .catch(err => console.error("Error:", err));
