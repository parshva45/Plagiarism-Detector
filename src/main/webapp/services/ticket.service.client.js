(function () {
    angular
        .module("BookYourTrip")
        .factory("TicketService", commentsService);

    function commentsService($http) {
        var selectedTicket = {};
        var api = {
            "findTicketById": findTicketById,
            "createTicket": createTicket,
            "findAllTicketsByUserId": findAllTicketsByUserId,
            "cancelTicket": cancelTicket,
            "getSelectedTicketDetails": getSelectedTicketDetails,
            "setSelectedTicketDetails": setSelectedTicketDetails
        };
        return api;

        function getSelectedTicketDetails() {
            return selectedTicket;
        }

        function setSelectedTicketDetails(value) {
            selectedTicket = value;
        }

        function findTicketById(ticketId) {
            return $http.get("/api/ticket/"+ticketId)
                .then(function (response) {
                    return response.data;
                });
        }

        function createTicket(ticket) {
            return $http.post("/api/ticket",ticket)
                .then(function (response) {
                    return response.data;
                });
        }

        function findAllTicketsByUserId(userId) {
            return $http.get("/api/ticket?userId="+userId)
                .then(function (response) {
                    return response.data;
                });
        }

        function cancelTicket(ticketId) {
            return $http.put('/api/ticket/'+ticketId)
                .then(function (response) {
                    return response.data;
                });
        }

    }
})();