// Главная таблица
async function getAllUsers() {
    /*
    let url = "/api/persons";
    let response = await fetch(url);
    let rJson = await response.json();
    let temp = '';
    rJson


     */


    await fetch("/api/persons")
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

//


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
    // let roles = [];
    // if (rolesArr.indexOf("ROLE_ADMIN") >= 0) {
    //     roles.push({'id': 1, 'name': 'ROLE_ADMIN'});
    // }
    //
    // if (rolesArr.indexOf("ROLE_USER") >= 0) {
    //     roles.push({'id': 2, 'name': 'ROLE_USER'});
    // }
    //
    // if (rolesArr.indexOf("ROLE_MANAGER") >= 0) {
    //     roles.push({'id': 3, 'name': 'ROLE_MANAGER'});
    // }

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
    //     .then(() => {
    //     document.getElementById("users-table").click();
    //
    // })
    $("#create-user.close").click();
    $("#show-users-table").click();
    // setTimeout(getAllUsers,4000);
    getAllUsers();
}

// Редактирование пользователя
async function editPerson(){

        let id_edit = document.getElementById("id_edit").value;
        let name = document.getElementById('name_edit').value;
        let surname = document.getElementById('surname_edit').value;
        let age = document.getElementById('age_edit').value;
        let username = document.getElementById('username_edit').value;
        let password = document.getElementById('password_edit').value;
        let roles = RolesArr(Array.from(document.getElementById('editRoles').selectedOptions)
            .map(role => role.value));


    await fetch("/api/persons", {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            'id' : id_edit,
            'name': name,
            'surname': surname,
            'age': age,
            'username': username,
            'password': password,
            'roles': roles
        })
    });

    $("#UserEditModal.close").click();
    // setTimeout(getAllUsers,4000);
    refreshTable();
}

function refreshTable(){
    getAllUsers();
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

