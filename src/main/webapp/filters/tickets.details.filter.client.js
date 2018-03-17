(function () {
    angular
        .module("PlagiarismDetector")
        .controller("TicketDetails", userLoader);

    function userLoader(TicketService) {
        var vm =this;
        vm.render = render;

        function init() {
        }
        init();


        function render() {
            TicketService
                .findTicketById(vm.ticket)
                .then(function (ticket) {
                    vm.ticketDetail = ticket;
                });

            if(vm.index % 2 === 0)
                vm.imageSrc = "../../../../../images/flight.jpg";
            else
                vm.imageSrc = "../../../../../images/flight2.jpg";
        }

    }
})();