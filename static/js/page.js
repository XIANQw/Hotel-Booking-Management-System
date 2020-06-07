$(function () {
  $("#ModeLogin").click(ModeSignIn);
  $("#ModeSignUp").click(ModeSignUp);
  $('#RessourceModeCreation').click(gotoCreationRessource);
  $('#RessourceQuitCreation').click(quitCreationRessource);
  $('#id_room').click(gotoCreationRoom)
  $('#id_house').click(gotoCreationHouse);
});


// modify gestionnaire.html
function ModeSignIn() {
  $('#zoneLogin').css('display', 'block');
  $('#zoneSignUp').css('display', 'none');
}


function ModeSignUp() {
  $('#zoneSignUp').css('display', 'block');
  $('#zoneLogin').css('display', 'none');
}

function gotoCreationRessource() {
  $('#createRessource').css('display', 'block');
  $('#infoRessource').css('display', 'none');
}

function quitCreationRessource() {
  $('#createRessource').css('display', 'none');
  $('#infoRessource').css('display', 'block');
}

function gotoCreationHouse() {
  $('#optionRoom').css('display', 'none');
  $('#optionHouse').css('display', 'block');
  $('#id_num_room').attr('required');
  $('#id_room_type').removeAttr('required');
}

function gotoCreationRoom() {
  $('#optionRoom').css('display', 'block');
  $('#optionHouse').css('display', 'none');
  $('#id_num_room').removeAttr('required');
  $('#id_room_type').attr('required');
}



