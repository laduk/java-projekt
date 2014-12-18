angular.module('huntApp.controllers', []).controller('CreatureListController',function($scope, $state, Creature){
    $scope.creatures = Creature.query();
    
})