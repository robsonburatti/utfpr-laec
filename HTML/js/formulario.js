function validarCampos() {
    var nome = document.getElementById("nome");
    var email = document.getElementById("email");
    var masculino = document.getElementById("masculino");
    var feminino = document.getElementById("feminino");
    var resultado = document.getElementById("resultado");

    resultado.innerHTML = "<p>";
    resultado.innerHTML += "O nome escolhido é: " + nome.value;
    resultado.innerHTML += "<br />";
    resultado.innerHTML += "O e-mail escolhido é: " + email.value;
    resultado.innerHTML += "<br />";

    resultado.innerHTML += "O sexo escolhido é: ";

    if (masculino.checked == true) {
        resultado.innerHTML += "Masculino";
    } else if (feminino.checked == true) {
        resultado.innerHTML += "Feminino";
    } else {
        resultado.innerHTML += "Não foi escolhido nenhuma opção";
    }

    resultado.innerHTML += "</p>";

    resultado.style.color = "white";

    return false;
}