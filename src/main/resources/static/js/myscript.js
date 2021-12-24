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

async function openModalDelete(id){
    let url = '/api/persons/' + id;
    let response = await fetch(url);
    let personjson = await response.json();


    // fetch("/api/persons/" + id)
    //     .then(res => {
    //     res.json().then(per => {
            document.getElementById("id_del").value = personjson.id;
            document.getElementById("name_del").value = personjson.name;
            document.getElementById("surname_del").value = personjson.surname;
            document.getElementById("username_del").value = personjson.username;
            document.getElementById("password_del").value = personjson.password;
            document.getElementById("age_del").value = personjson.age;
    //     })
    // });
}

// openModalEdit
// UserEditModal
async function openModalEdit(id){
    let url_edit = '/api/persons/' + id;
    let response_edit = await fetch(url_edit);
    let personjson_edit = await response_edit.json();

    // fetch("/api/persons/" + id)
    //     .then(res => {
    //     res.json().then(per => {
    document.getElementById("id_edit").value = personjson_edit.id;
    document.getElementById("name_edit").value = personjson_edit.name;
    document.getElementById("surname_edit").value = personjson_edit.surname;
    document.getElementById("username_edit").value = personjson_edit.username;
    document.getElementById("password_edit").value = personjson_edit.password;
    document.getElementById("age_edit").value = personjson_edit.age;
    //     })
    // });
}