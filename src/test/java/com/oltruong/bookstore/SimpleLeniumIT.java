package com.oltruong.bookstore;

import com.oltruong.bookstore.page.BookCreationPage;
import com.oltruong.bookstore.page.BookSearchPage;
import com.oltruong.bookstore.page.CustomerPage;

import net.codestory.simplelenium.SeleniumTest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;

import java.util.stream.IntStream;


public class SimpleLeniumIT extends SeleniumTest {

    private BookSearchPage bookSearchPage;
    private BookCreationPage bookCreationPage;
    private CustomerPage customerPage;

    private String[] authors = {"Jules Verne", "Robert C. Martin", "Victor Hugo", "JRR Tolkien", "Honoré de Balzac"};
    private String[] names = {"Bob", "Marcel", "Bill", "Claire", "Henriette"};
    private String[] address = {"Paris", "Chatenay Malabry", "Bourg La Reine", "Montrouge"};
    private String[] description = {"Trop de la balle", "Ca déchire grave", "Pas terrible", "Mouais j'ai connu mieux"};
    private String[] description2 = {" Achetez sans hésiter", " J'adore", " Fuyez", " Attendez qu'on vous l'offre"};

    private String[] bookname = {"Les aventures de ", "Le retour de ", "La vengeance de ", "Les origines de ", "Il était une fois "};
    private String[] bookname2 = {"Gérard Lambert", "Paul Bismuth", "l'Oncle Picsou", "Chuck Norris", "Jean-Claude", "Rodolphe", "Zlatan", "Candy", "Jupiter"};
    private String[] bookname3 = {" en vacances", " à la mer", " à la montagne", " en cours d'info", "  épisode VIII", " Origins", " Reboot", " Ragnarok", "GATE"};

    private String[] url = {
            "http://www.apprendre-en-ligne.net/bloginfo/images/humour/geek_martine-ecrit-en-utf-8.jpg",
            "http://media.paperblog.fr/i/389/3899431/martine-fait-rire-L-yhd636.jpeg",
            "http://ecx.images-amazon.com/images/I/51jPjhneLhL._SX377_BO1,204,203,200_.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/51KYdr4dXcL._SX210_.jpg"

    };

    @Ignore
    public void testInterface() {

        System.setProperty("browser", "chrome");
        driver().manage().window().maximize();
        goTo(bookSearchPage);
        bookSearchPage.checkTitle();


        IntStream.range(0, 10).forEach(i -> {
            goTo(bookCreationPage);
            bookCreationPage.setName(generateName());
            bookCreationPage.setAuthor(getRandom(authors));
            bookCreationPage.setDescription(getRandom(description) + getRandom(description2));
            bookCreationPage.setISBN(RandomStringUtils.randomNumeric(13));
            bookCreationPage.setPrice(RandomStringUtils.randomNumeric(2));
            bookCreationPage.setPictureURL(getRandom(url));
            bookCreationPage.create();
            bookSearchPage.checkTitle();

        });

        IntStream.range(0, 10).forEach(i -> {
            goTo(customerPage);

            int booksToBuy = RandomUtils.nextInt(5, 10);

            for (int j = 0; j < booksToBuy; j++) {
                customerPage.buyRandom(3);
            }
            customerPage.setName(getRandom(names));
            customerPage.setAddress(getRandom(address));
            customerPage.sendOrder();


        });

    }

    private String generateName() {
        return getRandom(bookname) + getRandom(bookname2) + getRandom(bookname3);
    }


    private String getRandom(String[] value) {
        int random = RandomUtils.nextInt(0, value.length);

        return value[random];
    }

    @Override
    protected String getDefaultBaseUrl() {
//        return "http://localhost:8081/angularjs/index.html#";
        return "http://localhost:8080/bookstore/angularjs/index.html#";
    }
}
