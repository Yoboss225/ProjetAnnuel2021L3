let pseudo = document.getElementById('pseudo');
pseudo.addEventListener('input', dispo);

function dispo(e) {
    let span = document.getElementById('exist');
    //Remise à zéro du contenu du span
    let value = e.currentTarget.value;

    //On vérifie tout d'abord si la longueur du pseudo est au moins de 4 caractères
    //Sinon utilisateur peut penser qu'il peut mettre moins de caractères
    if (value.length < e.currentTarget.minLength) {
        return;
    }

    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'index.php?route=loginExist&value=' + value);
    xhr.responseType = "text";
    //Si requête correctement executée
    xhr.addEventListener("load", function () {
        console.log(xhr.response);
        if (xhr.response === 'false') {
            //Le pseudo n'est pas utilisé
            let text = document.createTextNode('Pseudo disponible');
            span.className = 'noError';
            span.innerHTML = '';
            span.appendChild(text);
        } else if (xhr.response === 'true') {
            //Le pseudo existe déjà
            let text = document.createTextNode('Ce pseudo est déjà utilisé');
            span.className = 'error';
            span.innerHTML = '';
            span.appendChild(text);
        } else {
          console.log("Erreur de réponse du serveur sur disponibilité du login");
        }
    });
    //Si requête ratée
    xhr.addEventListener("error", function () {
        console.log('La requête se n\'est pas terminée');
    });
    //Si requête abandonnée
    xhr.addEventListener("abord", function () {
        console.log('La requête à été abandonnéé');
    });
    //Si requête timeout
    xhr.addEventListener("timeout", function () {
        console.log('Le serveur ne répond pas');
    });
    xhr.send();
}