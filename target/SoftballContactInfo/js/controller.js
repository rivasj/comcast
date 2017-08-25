angular.module('app', [])
.controller('MyCtrl', ['$scope','$http', function($scope, $http) {
	    
    $scope.getAllContactsUrl = "/SoftballContactInfo/rest/DB/getAllContacts";
    $scope.addContactUrl = "/SoftballContactInfo/rest/DB/addContact";
    $scope.deleteContactUrl = "/SoftballContactInfo/rest/DB/deleteContact";
    $scope.updateContactUrl = "/SoftballContactInfo/rest/DB/updateContact";
    
    $scope.positions = ['Outfield', 'Pitcher', '1st Base', '2nd Base', 'Short Stop', '3rd Base', 'Catcher', 'Designated Hitter'];
    
    $http.get($scope.getAllContactsUrl).then(function(response) {
    	$scope.contacts = response.data;
    }, function(response){
    	alert("getContacts failed: " + response.data);
    });
    
    $scope.addNewPlayer = function() {    	
    	if (typeof $scope.newContact == 'undefined' || $scope.newContact.name == null || $scope.newContact.name == '' || $scope.newContact.email == null || $scope.newContact.email == '' ||
    			$scope.newContact.phone == null || $scope.newContact.phone == '' || $scope.newContact.position == null || $scope.newContact.position == '') {
    		alert("All fields must be filled out before saving a player");
    	} else {    		
    		$http.post($scope.addContactUrl, $scope.newContact).then(function(response) {
    			alert("Add Player succeeded!")
            	$scope.contacts.push(angular.copy($scope.newContact));
            	$scope.newContact = null;
    		}, function(response) {
    			alert("Add Player failed!")
    		})
    		
    	}
    }
    
    $scope.deletePlayer = function(contact, index) {
    	if(confirm("Are you sure you want to delete player: " + contact.name)) {
    		if (contact.id == null) {
    			alert("In order to delete a newly added player, please refresh the page first.")
    		} else {
	    		$http.post($scope.deleteContactUrl, contact.id).then(function(response) {
	    			alert("Delete Player succeeded!")
	        		$scope.contacts.splice(index, 1);
	    		}, function(response) {
	    			alert("Delete Player failed!")
	    		})
    		}
    		
    	}
    }
    
    $scope.updatePlayer = function(contact) {
    	if (contact.name == null || contact.name == '' || contact.email == null || contact.email == '' ||
    			contact.phone == null || contact.phone == '' || contact.position == null || contact.position == '') {
    		alert("All fields must be filled out before saving a player");
    	} else {
    		// TODO update player in DB
    		
    		if (contact.id == null) {
    			alert("In order to update a newly added player, please refresh the page first.")
    		} else {
	    		$http.post($scope.updateContactUrl, contact).then(function(response) {
	    			alert("Update Player succeeded!")
	        		contact.editable = null;
	    		}, function(response) {
	    			alert("Update Player failed!")
	    		})
    		}
    		
    	}
    }
    
    $scope.selectContact = function(contact) {
    	$scope.selectedContact = contact;
    }
    
    $scope.editPlayer = function(contact) {
    	contact.editable = true;
    }
    
    $scope.cancelEditPlayer = function(contact) {
    	contact.editable = null;
    }
    
}]);
