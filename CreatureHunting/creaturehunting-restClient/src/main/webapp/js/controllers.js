angular.module('huntApp.controllers', [])
.controller('AreaListController',function($scope, $state, Area){
    $scope.areas = Area.getA();
})
.controller('CreatureListController',function($scope, $state, Creature){
    $scope.creatures = Creature.query();
});