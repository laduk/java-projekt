angular.module('huntApp.controllers', [])
.controller('AreaListController',function($scope, $state, Area){
    $scope.areas = Area.getAll();
})
.controller('AreaAddController', function($scope, $state, Area) {
    $scope.area = new Area();
    $scope.addArea = function(){
        $scope.area.$save(function() {
            $state.go('areas');
        });
    };
})
.controller('AreaDeleteController', function($scope, $state, $stateParams, Area){
    $scope.area = Area.get({ id: $stateParams.id });
    $scope.deleteArea = function(){
        $scope.area.$delete(function() {
            $state.go('areas');
        });
    };
})
.controller('AreaEditController',function($scope, $state, $stateParams, Area){
    $scope.area = Area.get({ id: $stateParams.id });
    $scope.updateArea = function(){ 
        $scope.area.$update(function(){
            $state.go('areas');
        });
    };
})
.controller('CreatureListController',function($scope, $state, Creature){
    $scope.creatures = Creature.getAll();

})
.controller('CreatureDeleteController', function($scope, $state, $stateParams, Creature){
    $scope.creature = Creature.get({ id: $stateParams.id });
    $scope.deleteCreature = function(){
        $scope.creature.$delete(function() {
            $state.go('creatures');
        });
    };
})
.controller('CreatureAddController', function($scope, $state, Creature, Area) {
    $scope.creature = new Creature();
    $scope.addCreature = function() {
        $scope.creature.$save(function() {
            $state.go('creatures');
        });
    };
})
.controller('CreatureEditController',function($scope, $state, $stateParams, Creature, Area){
    $scope.creature = Creature.get({ id: $stateParams.id });      
    $scope.updateCreature = function(){
        $scope.creature.$update(function(){
            $state.go('creatures');
        });
    };
});
