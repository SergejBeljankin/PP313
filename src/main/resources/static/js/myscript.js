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
                onclick="openModalEdit(${rJson[i].id});">Edit</button></td>
                <td>
                <button class="btn btn-danger btn-md" type="button"
                data-toggle="modal" data-target="#UserShowModal"
                onclick="openModalDelete(${rJson[i].id});">Delete</button></td>
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
    document.getElementById("button-delete").innerHTML = `<button type="button" class="btn btn-secondary" id="close_del" data-dismiss="modal">Close</button>
                <button class="btn btn-danger" type="submit">Delete</button>`;

}

// openModalEdit
// UserEditModal
async function openModalEdit(id){
    console.log("Открытие модального окна");
    let url_edit = '/api/persons/' + id;
    let response_edit = await fetch(url_edit);
    let personjson_edit = await response_edit.json();
    console.log(personjson_edit);
    document.getElementById("id_edit").value = personjson_edit.id;
    document.getElementById("name_edit").value = personjson_edit.name;
    document.getElementById("surname_edit").value = personjson_edit.surname;
    document.getElementById("username_edit").value = personjson_edit.username;
    document.getElementById("password_edit").value = personjson_edit.password;
    document.getElementById("age_edit").value = personjson_edit.age;

    // document.getElementById("button-edit").innerHTML = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
    //                     <button class="btn btn-primary" id="edit" onclick="editPerson2();">Edit</button>`;
    document.getElementById("button-edit").innerHTML = `<button type="button" class="btn btn-secondary" id="close" data-dismiss="modal">Close</button>
                        <button class="btn btn-primary" id="edit" type="submit">Edit</button>`;

}

// Удаление пользователя
document.getElementById("UserDelete")
    .addEventListener('submit', personDelete);
function personDelete(event){
    event.preventDefault();
    let id_del = document.getElementById('id_del').value;
    let url = "/api/persons/" + id_del;
    fetch(url,{
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
    });
    $("#close_del").click();
    $('#' + id_del).remove();
}

// Добавление нового пользователя
document.getElementById("new_Person")
    .addEventListener('submit', addNewPerson);
async function addNewPerson(event){
    event.preventDefault();
    //new_Person
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
    $("#users-table").click();
    getAllUsers();

}


// Редактирование пользователя 2
document.getElementById("UserEditModalForm")
    .addEventListener('submit', editPerson2);

async function editPerson2(event){
    event.preventDefault();
    let person = {
        id :  document.getElementById("id_edit").value,
        name :  document.getElementById('name_edit').value,
        surname :  document.getElementById('surname_edit').value,
        age :  document.getElementById('age_edit').value,
        username :  document.getElementById('username_edit').value,
        password :  document.getElementById('password_edit').value,
        roles : RolesArr(Array.from(document.getElementById('editRoles').selectedOptions)
            .map(role => role.value))
    }


    await fetch("/api/persons", {

        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(person)
    })
        .then(response => response.json())
        .then(json => {
            console.log('PUT: Получен ответ '); //тут в консоле покажет, что принят объект
            console.log(json);

            let param = '#' + json.id;
            console.log(param);
            $(param).remove();

            let html ='';
            html += '<tr id="'+ json.id +'">' +
                '<td>' + json.id + '</td>\n' +
                '<td>' + json.name + '</td>\n' +
                '<td>' + json.surname + '</td>\n' +
                '<td>' + json.age + '</td>\n' +
                '<td>' + json.username + '</td>\n' +
                '<td>' + json.roles.map(r => r.name.replace('ROLE_', '')).join(', ') + '</td>\n' +
                '<td>' + '<button class="btn btn-info btn-md" type="button" data-toggle="modal" data-target="#UserEditModal" onclick="openModalEdit('
                + json.id + ');">Edit</button></td>\n' +

                '<td>' + '<button class="btn btn-danger btn-md" type="button"  data-toggle="modal" data-target="#UserShowModal" onclick="openModalDelete(' +
                json.id + ');">Delete</button></td>\n' + '</tr>';

            $("#close").click();
            $('#PersonsTable').append(html);
        });




}














// Редактирование пользователя
async function editPerson(){
    setTimeout(10000);
    console.log("Нажата кнопка эдит");
    setTimeout(10000);
    let id_edit =  document.getElementById("id_edit").value;
    let name =  document.getElementById('name_edit').value;
    let surname =  document.getElementById('surname_edit').value;
    let age =  document.getElementById('age_edit').value;
    let username =  document.getElementById('username_edit').value;
    let password =  document.getElementById('password_edit').value;
    let roles = RolesArr(Array.from(document.getElementById('editRoles').selectedOptions)
        .map(role => role.value));

    setTimeout(10000);
    let person = {
        'id' : id_edit,
        'name': name,
        'surname': surname,
        'age': age,
        'username': username,
        'password': password,
        'roles': roles
    };


    console.log("собраны данные");
    setTimeout(10000);
    console.log(person);
    const person_edit = fetch("/api/persons", {
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
    console.log("запрос отправлен");
    setTimeout(1000);
    let person_edit_result = person_edit.json();
    setTimeout(1000);
    console.log(person_edit_result);
    console.log(person_edit_result.id);
    let param = '#' + person_edit_result.id;
    $(param).remove();


    /*
    let html ='';
    html += '<tr id="'+ person_edit_result.id +'">' +
    '<td>' + person_edit_result.id + '</td>\n' +
    '<td>' + person_edit_result.name + '</td>\n' +
    '<td>' + person_edit_result.surname + '</td>\n' +
    '<td>' + person_edit_result.age + '</td>\n' +
    '<td>' + person_edit_result.username + '</td>\n' +
    '<td>' + person_edit_result.roles.map(r => r.name.replace('ROLE_', '')).join(', ') + '</td>\n' +
    '<td>' + '<button class="btn btn-info btn-md" type="button" data-toggle="modal" data-target="#UserEditModal" onclick="openModalEdit('
         + person_edit_result.id + ');">Edit</button></td>\n' +

    '<td>' + '<button class="btn btn-danger btn-md" type="button"  data-toggle="modal" data-target="#UserShowModal" onclick="openModalDelete(' +
        person_edit_result.id + ');">Delete</button></td>\n' + '</tr>';

    console.log(html);

    // $("#UserEditModal.close").click();
    // $(param).replaceWith(html);
    //

    $('#PersonsTable').append(html);
    // getAllUsers();



     */
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