$.fn.getFormData = function() {
  var fields = this.find('[name]');
  var result = {};
  $.each(fields, function (i, el) {
    result[el.name] = el.value;
  });
  return result;
};
var config = {
  apiKey: "AIzaSyCmT7KU_k0dsm9s5oSyD7py8O-8owMw190",
  authDomain: "food-store-delivery-database.firebaseapp.com",
  databaseURL: "https://food-store-delivery-database.firebaseio.com",
  projectId: "food-store-delivery-database",
  storageBucket: "food-store-delivery-database.appspot.com",
  messagingSenderId: "645899371811"
};
var fb = firebase.initializeApp(config);
var user_id;
$('.submit').on('click', function (e) {
  if (document.getElementById("storename").value == "") {
    alert("Please enter a store name");
    return;
  }
  e.preventDefault();
  var updates = {};
  user_id = "Location";
  updates[user_id] = $('form').getFormData();
  var path = 'Supermarkets/' + document.getElementById("storename").value;
  fb.database().ref().child(path).update(updates);
});
