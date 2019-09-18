"use strict";
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//+                     LOADING                       +
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
function showLoadingModal() {
  $("#LoadingModal").modal("show");
}
function hideLoadingModal() {
  $("#LoadingModal").modal("hide");
}

function showModal(id) {
  $("#" + id).modal("show");
}
function shideModal(id) {
  $("#" + id).modal("hide");
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//+               CONFIRMATION                       +
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
function showConfirmationModal() {
  $("#modalConfirmation").modal("show");
}
function hideConfirmationModal() {
  $("#modalConfirmation").modal("hide");
}

function showConfirmationModalById(id) {
  $("#" + id).modal("show");
}
function hideConfirmationModalById(id) {
  $("#" + id).modal("hide");
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//+                  UTILITAIRES                      +
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
function ScapString(msg) {
  if (typeof msg === undefined) {
      return "";
  }
  var sources = ['é', 'è', 'ê', 'à', 'ç', 'æ', 'ï', 'ù', 'œ'];
  var destinations = ["&eacute;", "&egrave;", "&ecirc;", "&agrave;", "&ccedil;", "&aelig;", "&iuml;", "&ugrave;", "&oelig;"];
  for (var j = 0; j < sources.length; j++) {
      for (var i = 0; i < sources.length; i++) {
          msg = msg.replace(sources[i], destinations[i]);
      }
  }
  return msg;

}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//+                  NOTIFICATIONS                    +
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
function showNotification(msg, type) {
  if (!(new RegExp(/^(success|normal|error|warning)$/).test(type))) {
      type = "normal";
  }
  msg = ScapString(msg);

  if (type === "success") {
      $.growl.notice({ message: msg });
  } else if (type === "error") {
      $.growl.error({ message: msg });
  } else if (type === "warning") {
      $.growl.warning({ message: msg });
  } else {
      $.growl({ title: "Information", message: msg });
  }
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//+                     FONCTIONS                      +
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
function sendAjaxRequest(type, url, data) {
  var contextPath = "/";
  $.ajax({
      type: type,
      url: contextPath + url,
      timeout: 900000,
      data: data,
      dataType: 'json',
      beforeSend: function () {
          showLoadingModal();
      },
      success: function (response) {
          if (response.status === "200") {
              showNotification(response.message, "success");
              setTimeout(function () {
                  location.reload();
              }, 2000);
          } else {
              hideLoadingModal();
              showNotification(response.message, "warning");
          }
      },
      error: function () {
          hideLoadingModal();
          showNotification("Une erreur s'est produite", "error");
      }
  });
}
function sendAjaxPostRequest(url, data) {
  var contextPath = "/";
  $.ajax({
      type: "POST",
      url: contextPath + url,
      timeout: 900000,
      data: JSON.stringify(data),
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      beforeSend: function (xhr) {
          showLoadingModal();
      },
      success: function (response) {
          if (response.Code === 200) {
              showNotification(response.Message, "success");
              setTimeout(function () {
                  location.reload();
              }, 2000);
          } else {
              showNotification(response.Message, "warning");
          }
          hideLoadingModal();
      },
      error: function () {
          hideLoadingModal();
          showNotification("Une erreur s'est produite", "error");
      }
  });
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++
//+                       JQUERY                      +
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
$(document).ready(function () {
	$(document).on('click', '#btnShowModalAddNewHypothese', function (e) {
        showModal("modalAddNewAuteur");
        setTimeout(function () {
            $("#natureHypothese").val("");
            $("#quantificationHypothese").val("");
            $("#elementDHypothese").val("");            
            $("#formAddNewHypothese").trigger("reset");
        }, 10);
    });
	
	$(document).on('click', '#btnShowModalAddNewAuteur', function (e) {
        showModal("modalAddNewAuteur");
        setTimeout(function () {
            $("#prenom").val("");
            $("#nom").val("");
            $("#email").val("");
            $("#laboratoire").val("");
            $("#institution").val("");
            $("#pays").val("");
            $("#formAddNewAuteur").trigger("reset");
        }, 10);
    });			
});