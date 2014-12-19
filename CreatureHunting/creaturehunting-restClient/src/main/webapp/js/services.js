angular.module('huntApp.services', [])
.factory('Area', function($resource){
    return $resource('http://localhost:8080/pa165/rest/areas/:id',{id: '@id'}, {
        query: {
            method: 'GET',
            isArray:true,
            interceptor: {responseError : showAlert}            
        },
        getAll: {
            method: 'GET', isArray:true, 
            interceptor: {responseError : showAlert}
        },
        get: {
            method: 'GET', 
            interceptor: {responseError  : showAlert}         
        },
        save: {
            method: 'POST', 
            interceptor: {responseError : showAlert}  
        },
        update: {
            method: 'PUT',
            interceptor: {responseError : showAlert}
        },       
        remove: {
              method: 'DELETE',
            interceptor: {responseError : showAlert}  
        }
    });
})
        
.factory('Creature', function($resource){
    return $resource('http://localhost:8080/pa165/rest/creatures/:id',{id: '@id'}, {        
        query: {
            method: 'GET',
            isArray:true,
            interceptor: {responseError : showAlert}            
        },
        getAll: {
            method: 'GET', isArray:true, 
            interceptor: {responseError : showAlert}
        },
        get: {
            method: 'GET', 
            interceptor: {responseError  : showAlert}         
        },
        save: {
            method: 'POST', 
            interceptor: {responseError : showAlert}  
        },
        update: {
            method: 'PUT',
            interceptor: {responseError : showAlert}
        },       
        remove: {
            method: 'DELETE',
            interceptor: {responseError : showAlert}  
        }
    });
});

function showAlert(){alert("Service is unavailable, try it again.");}