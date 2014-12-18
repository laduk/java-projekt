angular.module('huntApp', ['ngResource','ui.router','huntApp.services','huntApp.controllers','checklist-model']);

angular.module('huntApp').config(function($stateProvider) {
    $stateProvider.state('index', {
        url: '/'
    }).state('creatures', {
        url: '/creatures',
        templateUrl: 'creatures.html',
        controller: 'CreatureListController'
    }).state('newCreature', {
        url: '/creatures/new',
        templateUrl: 'creatures_add.html',
        controller: 'CreatureAddController'
    }).state('editCreature', {
        url: '/creatures/edit',
        templateUrl: 'creatures_edit.html',
        controller: 'CreatureEditController'
    }).state('deleteCreature', {
        url: '/creatures/delete',
        templateUrl: 'creatures_delete.html',
        controller: 'CreatureDeleteController'
    }).state('areas', {
        url: '/areas',
        templateUrl: 'areas.html',
        controller: 'AreaListController'
    }).state('newArea', {
        url: '/areas/new',
        templateUrl: 'areas_add.html',
        controller: 'AreaAddController'
    }).state('editArea', {
        url: '/areas/edit',
        templateUrl: 'areas_edit.html',
        controller: 'AreaEditController'
    }).state('deleteArea', {
        url: '/areas/delete',
        templateUrl: 'areas_delete.html',
        controller: 'AreaDeleteController'
    });
    
    
}).run(function($state) {
  $state.go('index');
});