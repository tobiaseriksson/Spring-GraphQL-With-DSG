package se.tsoft.spring.graphql.dsg.demo;

import se.tsoft.spring.graphql.dsg.demo.model.Address;
import se.tsoft.spring.graphql.dsg.demo.model.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateData {


    private List<String> firstNames;
    private List<String> lastNames;
    private List<String> cities;
    private List<String> streetNames;
    Random random = new Random();

    private String pickRandom(List<String> lst) {
        return lst.get(random.nextInt(lst.size()));
    }

    /**
     * 5 digit zipcode
     * @return
     */
    public String zipCode() {
        int zipcode = 1000 + random.nextInt(8999);
        return ""+zipcode;
    }

    public String street() {
        return pickRandom(streetNames)+" "+random.nextInt(100);
    }

    public String city() {
        return pickRandom(cities);
    }

    public String firstName() {
        return pickRandom(firstNames);
    }

    public String lastName() {
        return pickRandom(lastNames);
    }

    public void init() {
        String txtFirstNames = Utils.readFileFromResources("firstnames_boys.txt");
        txtFirstNames = txtFirstNames + "\n" + Utils.readFileFromResources("firstnames_girls.txt");
        firstNames = Arrays.stream(txtFirstNames.split("\n")).filter( row -> row.trim().length() > 0 ).collect(Collectors.toList());

        String txtLastNames = Utils.readFileFromResources("lastnames.txt");
        lastNames =
                        Arrays.stream(txtLastNames.split("\n")).filter( row -> row.trim().length() > 0 ).collect(Collectors.toList());

        String citiesTxt = Utils.readFileFromResources("städer.txt");
        cities = Arrays.stream(citiesTxt.split("\n")).filter( row -> row.trim().length() > 0 ).collect(Collectors.toList());

        String txtsStreetNames = Utils.readFileFromResources("gatunamn_göteborg.txt");
        txtsStreetNames = txtsStreetNames + Utils.readFileFromResources("gatunamn_stockholm.txt");
        streetNames = Arrays.stream(txtsStreetNames.split("\n")).filter( row -> row.trim().length() > 0 ).collect(Collectors.toList());

    }

    public List<Customer> generateNCustomers(int N) {
        return IntStream.range(1,N).mapToObj( r -> new Customer(r,firstName(),lastName(),r)).collect(Collectors.toList());
    }

    public List<Address> generateNAddresses(int N) {
        return IntStream.range(1,N).mapToObj( r -> new Address(r,street(),zipCode(),city(),"Sweden")).collect(Collectors.toList());
    }



    public static void demo() {
        GenerateData generator = new GenerateData();
        generator.init();

        // IntStream.range(1,100).mapToObj( r -> generator.firstName()+" "+generator.lastName()).forEach( n -> System.out
        // .println(n));
        generator.generateNCustomers(10).forEach( u -> System.out.println(u));
        generator.generateNAddresses(10).forEach( u -> System.out.println(u));

    }

    public static void main(String[] args) {
        demo();

    }
}
