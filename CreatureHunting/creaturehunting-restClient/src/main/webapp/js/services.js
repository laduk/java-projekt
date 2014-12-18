angular.module('huntApp.services', [])
.factory('Creature', function($resource){
    return $resource('http://localhost\:8080/pa165/rest/creatures/:id',{id: '@id'}, {
//          update: {
//              method: 'PUT',
//            interceptor: {responseError : showAlert}
//          },
          query: {
              method: 'GET', isArray:true, 
              interceptor: {responseError : showAlert}
          }//,
//          get: {
//               method: 'GET', 
//              interceptor: {responseError  : showAlert}         
//          },
//          save: {
//              method: 'POST', 
//              interceptor: {responseError : showAlert}  
//          },
//          remove: {
//              method: 'DELETE',
//            interceptor: {responseError : showAlert}  
//          }
    });
})