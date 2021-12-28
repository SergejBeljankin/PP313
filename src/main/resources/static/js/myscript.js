// Главная таблица
async function getAllUsers() {

    let url = "/api/persons";
    let response = await fetch(url);
    let rJson = await response.json();
    let hmtlblok = '';
    for (i = 0; i < rJson.length; i++){
        hmtlblok += `<tr id="${rJson[i].id}">
                <td>${rJson[i].id}</td>
                <td>${rJson[i].name}</td>
                <td>${rJson[i].surname}</td>
                <td>${rJson[i].age}</td>
                <td>${rJson[i].username}</td>
                <td>${rJson[i].roles.map(r => r.name.replace('ROLE_', '')).join(', ')}</td>
                <td>
                <button class="btn btn-info btn-md" type="button"
                data-toggle="modal" data-target="#UserEditModal"
                onclick="openModalEdit(${rJson[i].id})">Edit</button></td>
                <td>
                <button class="btn btn-danger btn-md" type="button"
                data-toggle="modal" data-target="#UserShowModal"
                onclick="openModalDelete(${rJson[i].id})">Delete</button></td>
              </tr>`
    }
    document.getElementById("PersonsTable").innerHTML = hmtlblok;
}
getAllUsers()


// -------------- удаление пользователя
// -------------- UserShowModal

async function openModalDelete(id){
    let url = '/api/persons/' + id;
    let response =  await fetch(url);
    let personjson =  await response.json();

    document.getElementById("id_del").value = personjson.id;
    document.getElementById("name_del").value = personjson.name;
    document.getElementById("surname_del").value = personjson.surname;
    document.getElementById("username_del").value = personjson.username;
    document.getElementById("password_del").value = personjson.password;
    document.getElementById("age_del").value = personjson.age;
    document.getElementById("button-delete").innerHTML = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button class="btn btn-danger" onclick="personDelete(${personjson.id})">Delete</button>`;

}

// openModalEdit
// UserEditModal
async function openModalEdit(id){
    let url_edit = '/api/persons/' + id;
    let response_edit = await fetch(url_edit);
    let personjson_edit = await response_edit.json();

    document.getElementById("id_edit").value = personjson_edit.id;
    document.getElementById("name_edit").value = personjson_edit.name;
    document.getElementById("surname_edit").value = personjson_edit.surname;
    document.getElementById("username_edit").value = personjson_edit.username;
    document.getElementById("password_edit").value = personjson_edit.password;
    document.getElementById("age_edit").value = personjson_edit.age;

    document.getElementById("button-edit").innerHTML = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button class="btn btn-primary" onclick="editPerson()">Edit</button>`;

}

// Удаление пользователя
function personDelete(id){
    let url = "/api/persons/" + id;
    fetch(url,{
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
    });
    $("#UserShowModal.close").click();

    getAllUsers();
}

// Добавление нового пользователя
async function addNewPerson(){

    let name = document.getElementById('name').value;
    let surname = document.getElementById('surname').value;
    let age = document.getElementById('age').value;
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    let roles = RolesArr(Array.from(document.getElementById('rolesNames').selectedOptions)
        .map(role => role.value));

    let newPerson = fetch("/api/persons", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            'name': name,
            'surname': surname,
            'age': age,
            'username': username,
            'password': password,
            'roles': roles
        })
    });
    alert(newPerson);
    console.log(newPerson);

    $("#create-user.close").click();
    $("#show-users-table").click();
    getAllUsers();

}

// Редактирование пользователя
async function editPerson(){

    let id_edit =  document.getElementById("id_edit").value;
    let name =  document.getElementById('name_edit').value;
    let surname =  document.getElementById('surname_edit').value;
    let age =  document.getElementById('age_edit').value;
    let username =  document.getElementById('username_edit').value;
    let password =  document.getElementById('password_edit').value;
    let roles = RolesArr(Array.from(document.getElementById('editRoles').selectedOptions)
        .map(role => role.value));

    let person = {
        'id' : id_edit,
        'name': name,
        'surname': surname,
        'age': age,
        'username': username,
        'password': password,
        'roles': roles
    }



    let person_edit = await fetch("/api/persons", {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(person)
    });
    let person_edit_result = await person_edit.json();

    let param = '#' + person_edit_result.id;
    // $(param).remove();

    console.log(person_edit_result);

    let html = `<td>${person_edit_result.id}</td>
    <td>${person_edit_result.name}</td>
    <td>${person_edit_result.surname}</td>
    <td>${person_edit_result.age}</td>
    <td>${person_edit_result.username}</td>
    <td>${person_edit_result.roles.map(r => r.name.replace('ROLE_', '')).join(', ')}</td>
    <td>
        <button class="btn btn-info btn-md" type="button" data-toggle="modal" data-target="#UserEditModal" onclick="openModalEdit(${person_edit_result.id})">Edit</button>
    </td>
    <td>
        <button class="btn btn-danger btn-md" type="button"  data-toggle="modal" data-target="#UserShowModal" onclick="openModalDelete(${person_edit_result.id})">Delete</button>
    </td>`;


    // $("#UserEditModal.close").click();
    // $(param).replaceWith(html);
    //

    $('#PersonsTable>tr').remove();
    // getAllUsers();


    //
    // editRow(person_edit_result.id);
}

async function editRow(id){

    let url_edit = '/api/persons/' + id;
    console.log(url_edit);
    let response_edit = await fetch(url_edit);
    let personjson_edit = await response_edit.json();

    let html = `<td>${personjson_edit.id}</td>
    <td>${personjson_edit.name}</td>
    <td>${personjson_edit.surname}</td>
    <td>${personjson_edit.age}</td>
    <td>${personjson_edit.username}</td>
    <td>${personjson_edit.roles.map(r => r.name.replace('ROLE_', '')).join(', ')}</td>
    <td>
        <button class="btn btn-info btn-md" type="button" data-toggle="modal" data-target="#UserEditModal" onclick="openModalEdit(${personjson_edit.id})">Edit</button>
    </td>
    <td>
        <button class="btn btn-danger btn-md" type="button"  data-toggle="modal" data-target="#UserShowModal" onclick="openModalDelete(${personjson_edit.id})">Delete</button>
    </td>`;
    console.log(html);
    console.log(`${personjson_edit.id}`);

    // document.getElementById(`${personjson_edit.id}`).innerHTML = html;


    $('#' + `${personjson_edit.id}`+'>tr').replaceWith(html);
}

// Создание массива ролей

function RolesArr(arr) {
    let roles = [];

    if (arr.indexOf("ROLE_ADMIN") >= 0) {
        roles.push({'id': 1, 'name': 'ROLE_ADMIN'});
    }

    if (arr.indexOf("ROLE_USER") >= 0) {
        roles.push({'id': 2, 'name': 'ROLE_USER'});
    }

    if (arr.indexOf("ROLE_MANAGER") >= 0) {
        roles.push({'id': 3, 'name': 'ROLE_MANAGER'});
    }

    return roles;
}