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
.controller('CreatureListController',function($scope, $state, Creature){
    $scope.creatures = Creature.getAll();
});
