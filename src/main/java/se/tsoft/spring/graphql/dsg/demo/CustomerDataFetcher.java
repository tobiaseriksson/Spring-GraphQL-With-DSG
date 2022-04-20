package se.tsoft.spring.graphql.dsg.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import se.tsoft.spring.graphql.dsg.demo.model.Customer;
import se.tsoft.spring.graphql.dsg.demo.model.CustomerInput;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DgsComponent
public class CustomerDataFetcher {

    private final List<Customer> customers = new LinkedList<>();

    @Autowired
    public CustomerDataFetcher() {
        customers.add(new Customer(400, "Bob", "Lee Swagger"));
        customers.add(new Customer(401, "Marcus", "Lutrell"));
        customers.add(new Customer(402, "Chris", "kyle"));
    }

    @DgsQuery
    public List<Customer> customers(@InputArgument String nameFilter) {
        if (nameFilter == null) {
            return customers;
        }
        return customers.stream()
                        .filter(s -> s.getFirstName().contains(nameFilter) || s.getLastName().contains(nameFilter))
                        .collect(Collectors.toList());
    }

    @DgsData(parentType = "Mutation", field = "addCustomer")
    public Customer addCustomer(DataFetchingEnvironment dataFetchingEnvironment) {
        Map<String, Object> input = dataFetchingEnvironment.getArgument("customer");
        CustomerInput ci = new ObjectMapper().convertValue(input, CustomerInput.class);

        // sample validation, if firstname contains a 'z' then do not allow it.
        if (ci.getFirstName().contains("z")) {
            throw new IllegalArgumentException("Firstname cannot contain a 'z'");
        }
        Integer max = customers.stream().mapToInt(Customer::getId).max().orElse(0);
        Customer newCustomer = new Customer(max + 1, ci.getFirstName(), ci.getLastName());
        customers.add(newCustomer);
        return newCustomer;
    }
}
