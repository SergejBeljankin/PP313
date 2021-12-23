// Главная таблица
async function getAllUsers() {
    fetch("/api/persons")
        .then(res => res.json())
        .then(persons => {
            let temp = '';
            persons.forEach(function (persons) {
                temp += `
                <tr>
                <td>${persons.id}</td>
                <td>${persons.name}</td>
                <td>${persons.surname}</td>
                <td>${persons.age}</td>
                <td>${persons.username}</td>
                <td>${persons.roles.map(r => r.name.replace('ROLE_', '')).join(', ')}</td>
                <td>
                <button class="btn btn-info btn-md" type="button"
                data-toggle="modal" data-target="#UserEditModal"
                onclick="openModalEdit(${persons.id})">Edit</button></td>
                <td>
                <button class="btn btn-danger btn-md" type="button"
                data-toggle="modal" data-target="#UserShowModal"
                onclick="openModalDelete(${persons.id})">Delete</button></td>
              </tr>`;
            });
            document.getElementById("PersonsTable").innerHTML = temp;
        });
}
getAllUsers()

// -------------- UserShowModal

function openModalDelete(id){
    fetch("/api/persons/" + id)
        .then(res => {
        res.json().then(person => {
            document.getElementById("id_edit").value = person.id;
            document.getElementById("name_edit").value = person.name;
            document.getElementById("surname_edit").value = person.surname;
            document.getElementById("username_edit").value = person.username;
            document.getElementById("age_edit").value = person.age;
        })
    });
}
