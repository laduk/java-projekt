angular.module('huntApp.controllers', [])
.controller('AreaListController',function($scope, $state, Area){
    $scope.areas = Area.getAll();
})
.controller('CreatureListController',function($scope, $state, Creature){
    $scope.creatures = Creature.getAll();
});