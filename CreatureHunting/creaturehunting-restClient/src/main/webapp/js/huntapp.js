angular.module('huntApp', ['ngResource','ui.router','huntApp.services','huntApp.controllers','checklist-model']);

angular.module('huntApp').config(function($stateProvider) {
    $stateProvider.state('index', {
        url: '/'
    }).state('creatures', {
        url: '/creatures',
        templateUrl: 'creatures.html',
        controller: 'CreatureListController'
    });
    
}).run(function($state) {
  $state.go('index'); //make a transition to creature state when app starts
});