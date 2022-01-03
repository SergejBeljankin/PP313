// PersonInfo
async function PersonInfo(){


    let url_info = '/api/info';
    let personId = await fetch(url_info);
    let persIdJS = await personId.json();


    $('#username_in')[0].innerHTML = persIdJS.username;
    $('#role')[0].innerHTML = persIdJS.roles.map(r => r.name.replace('ROLE_', '')).join(', ');

    console.log(persIdJS);
    let html_bloc = "<tr><td>" + persIdJS.id + "</td>" +
        "<td>" + persIdJS.name + "</td>" +
        "<td>" + persIdJS.surname + "</td>" +
        "<td>" + persIdJS.age + "</td>" +
        "<td>" + persIdJS.username + "</td>" +
        "<td>" + persIdJS.roles.map(r => r.name.replace('ROLE_', '')).join(', ') + "</td></tr>";

    document.getElementById("UserInfo").innerHTML = html_bloc;

}
PersonInfo();