const API_URL = "http://localhost:8080/inventory/v1";

// Fetch all items
async function loadItems() {
    const response = await fetch(`${API_URL}/getAll`);
    const items = await response.json();
    const tbody = document.getElementById('itemBody');
    if (!tbody) return;

    tbody.innerHTML = items.map(item => `
        <tr>
            <td>${item.id}</td>
            <td>${item.itemName}</td>
            <td>${item.category}</td>
            <td>${item.quantity}</td>
            <td>$${item.price}</td>
            <td>${item.supplier}</td>
            <td>
                <button class="btn btn-edit" onclick="goToEdit(${item.id})">Edit</button>
                <button class="btn btn-delete" onclick="deleteItem(${item.id})">Delete</button>
            </td>
        </tr>
    `).join('');
}

function goToEdit(id) {
    window.location.href = `add-item.html?edit=${id}`;
}

async function prefillForm(id) {
    const response = await fetch(`${API_URL}/get/${id}`);
    const item = await response.json();
    document.getElementById('id').value = item.id;
    document.getElementById('id').disabled = true;
    document.getElementById('itemName').value = item.itemName;
    document.getElementById('category').value = item.category;
    document.getElementById('quantity').value = item.quantity;
    document.getElementById('price').value = item.price;
    document.getElementById('supplier').value = item.supplier;
}

async function saveItem(data, editId) {
    const url = editId ? `${API_URL}/update/${editId}` : `${API_URL}/addItem`;
    const method = editId ? 'PUT' : 'POST';
    const response = await fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    });
    return response.ok;
}

async function deleteItem(id) {
    if (confirm("Delete this item?")) {
        await fetch(`${API_URL}/delete/${id}`, { method: 'DELETE' });
        loadItems();
    }
}
