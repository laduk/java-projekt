angular.module('huntApp', ['ngResource','ui.router','huntApp.services','huntApp.controllers']);

angular.module('huntApp').config(function($stateProvider) {
    $stateProvider.state('index', {
        url: '/'
    }).state('creatures', {
        url: '/creatures',
        templateUrl: 'creatures.html',
        controller: 'CreatureListController'
    }).state('newCreature', {
        url: '/creatures/new',
        templateUrl: 'creature_add.html',
        controller: 'CreatureAddController'
    }).state('editCreature', {
        url: '/creatures/:id/edit',
        templateUrl: 'creature_edit.html',
        controller: 'CreatureEditController'
    }).state('deleteCreature', {
        url: '/creatures/:id/delete',
        templateUrl: 'creature_delete.html',
        controller: 'CreatureDeleteController'
    }).state('areas', {
        url: '/areas',
        templateUrl: 'areas.html',
        controller: 'AreaListController'
    }).state('newArea', {
        url: '/areas/new',
        templateUrl: 'area_add.html',
        controller: 'AreaAddController'
    }).state('editArea', {
        url: '/areas/:id/edit',
        templateUrl: 'area_edit.html',
        controller: 'AreaEditController'
    }).state('deleteArea', {
        url: '/areas/:id/delete',
        templateUrl: 'area_delete.html',
        controller: 'AreaDeleteController'
    });
    
    
}).run(function($state) {
  $state.go('index');
});
