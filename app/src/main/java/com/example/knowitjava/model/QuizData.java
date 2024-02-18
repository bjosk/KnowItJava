package com.example.knowitjava.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class QuizData {

    public static ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();

//        Quiz infrastructureQuiz = new Quiz("IT infrastructure", new LinkedList<>(Arrays.asList(
//                new Question("What is the capital of Sweden?", new String[]{"Oslo", "Stockholm", "Copenhagen", "Helsinki"}, 1),
//                new Question("What is the capital of Germany?", new String[]{"Berlin", "Amsterdam", "Rotterdam", "Antwerpen"}, 0)
//        )));
//
//        Quiz softwareDevQuiz = new Quiz("Software Development Fundamentals", new LinkedList<>(Arrays.asList(
//                new Question("Which language runs in a web browser?", new String[]{"Java", "C#", "Python", "JavaScript"}, 3),
//                new Question("What does 'MVC' stand for?", new String[]{"Model View Controller", "Multiple View Control", "Model View Control", "Main Virtual Control"}, 0)
//        )));

        Quiz basicProgrammingQuiz = new Quiz("Basic Programming Concepts", new LinkedList<>(Arrays.asList(
                new Question("What is a variable in programming?", new String[]{"A storage location with a fixed value", "A type of function", "A storage location that can be changed", "An operation in math"}, 2),
                new Question("Which of the following is a loop structure in programming?", new String[]{"If-Else", "Switch-Case", "For", "Try-Catch"}, 2),
                new Question("What does 'IDE' stand for in software development?", new String[]{"Integrated Development Environment", "Internal Device Emulator", "Intelligent Design Engine", "Integrated Device Engineering"}, 0),
                new Question("Which data type is typically used to store a boolean value?", new String[]{"int", "boolean", "string", "char"}, 1),
                new Question("What is encapsulation in Object-Oriented Programming?", new String[]{"The process of creating threads", "The inclusion of data and methods within a class", "The conversion of one type of object into another", "The process of breaking a program into modules"}, 1),
                new Question("Which keyword is used to create a new instance of an object in Java?", new String[]{"struct", "class", "new", "object"}, 2),
                new Question("What is recursion in programming?", new String[]{"A syntax error", "A function that calls itself", "A loop that never ends", "A method of iterating over an array"}, 1),
                new Question("Which of the following is NOT a primitive data type in Java?", new String[]{"String", "int", "boolean", "double"}, 0),
                new Question("What does 'API' stand for?", new String[]{"Application Programming Interface", "Application Protocol Integration", "Advanced Programming Interface", "Automated Programming Instructions"}, 0),
                new Question("What is the purpose of the 'git pull' command?", new String[]{"To send local commits to a remote repository", "To delete a branch", "To merge a remote branch into the current branch", "To copy a repository into a new directory"}, 2)
        )));

        Quiz webDevelopmentQuiz = new Quiz("Web Development Fundamentals", new LinkedList<>(Arrays.asList(
                new Question("What does HTML stand for?", new String[]{"HyperText Markup Language", "Hyperlinking and Text Markup Language", "Hyperlink Markup Language", "HighText Machine Language"}, 0),
                new Question("Which HTML tag is used to define an internal style sheet?", new String[]{"<script>", "<css>", "<style>", "<link>"}, 2),
                new Question("Which property is used to change the background color?", new String[]{"bgcolor", "color", "background-color", "color-background"}, 2),
                new Question("How do you add a comment in a CSS file?", new String[]{"// this is a comment", "/* this is a comment */", "<!-- this is a comment -->", "' this is a comment"}, 1),
                new Question("Which HTML attribute specifies an alternate text for an image, if the image cannot be displayed?", new String[]{"title", "src", "alt", "href"}, 2),
                new Question("Which doctype is correct for HTML5?", new String[]{"<!DOCTYPE HTML PUBLIC>", "<!DOCTYPE html>", "<!DOCTYPE html5>", "<!HTML>"}, 1),
                new Question("How do you select an element with id 'demo' in CSS?", new String[]{"demo", ".demo", "#demo", "*demo"}, 2),
                new Question("How do you insert a JavaScript into an HTML page?", new String[]{"<js>", "<scripting>", "<javascript>", "<script>"}, 3),
                new Question("Which object is the topmost object in the browser's object hierarchy?", new String[]{"Document", "Window", "Form", "Browser"}, 1),
                new Question("Which tag is used to specify a footer for a document or section?", new String[]{"<bottom>", "<footer>", "<section>", "<foot>"}, 1),
                new Question("What is the default value of the position property?", new String[]{"fixed", "static", "relative", "absolute"}, 1),
                new Question("Which HTML tag is used to define a list item?", new String[]{"<li>", "<ul>", "<item>", "<list>"}, 0),
                new Question("What does CSS stand for?", new String[]{"Creative Style Sheets", "Cascading Style Sheets", "Colorful Style Sheets", "Computer Style Sheets"}, 1),
                new Question("Which property is used to change the font of an element?", new String[]{"font-style", "font-family", "text-style", "font-weight"}, 1),
                new Question("How can you make a numbered list?", new String[]{"<ul>", "<ol>", "<list>", "<dl>"}, 1)
        )));

        Quiz cyberSecurityQuiz = new Quiz("Cyber Security Fundamentals", new LinkedList<>(Arrays.asList(
                new Question("What is phishing?", new String[]{"A technique to gain personal information for the purpose of identity theft", "A type of fish found in the Atlantic Ocean", "The process of optimizing websites to rank more highly in search engine results", "A legitimate way to access user data with their consent"}, 0),
                new Question("What does 'VPN' stand for?", new String[]{"Virus Protection Needed", "Very Private Network", "Virtual Private Network", "Variable Path Network"}, 2),
                new Question("Which of these is considered a strong password?", new String[]{"123456", "password", "letmein", "Tr0ub4dor&3"}, 3),
                new Question("What is malware?", new String[]{"Software used to perform malicious tasks", "A new operating system", "A marketing strategy", "An email service"}, 0),
                new Question("Which of the following is NOT a type of cybersecurity threat?", new String[]{"Trojan Horse", "Adware", "Phishing", "SEO"}, 3),
                new Question("What is the primary purpose of a firewall?", new String[]{"To detect and remove viruses", "To boost the speed of internet connections", "To block unauthorized access to a network", "To filter spam from emails"}, 2),
                new Question("What does 'SSL' stand for?", new String[]{"Secure Socket Layer", "Secure System Login", "System Socket Layer", "Safe Site Login"}, 0),
                new Question("Which of these is a common indicator of a phishing attempt?", new String[]{"Emails from known colleagues", "A website with a valid SSL certificate", "A request for personal information via email", "An email that contains no attachments"}, 2),
                new Question("What is encryption?", new String[]{"Converting information into an unsecure format", "The process of removing viruses", "Converting information into a secure format", "A method to increase internet speed"}, 2),
                new Question("Which is NOT a recommended security practice?", new String[]{"Using the same password for multiple accounts", "Regularly updating software", "Enabling two-factor authentication", "Backing up data regularly"}, 0),
                new Question("What is a zero-day exploit?", new String[]{"A vulnerability unknown to the software vendor", "An exploit that takes multiple days to be effective", "A term used to describe outdated software", "A security patch released by developers"}, 0),
                new Question("Which protocol is used to securely access a remote computer?", new String[]{"HTTP", "SSH", "FTP", "SMTP"}, 1),
                new Question("What is a botnet?", new String[]{"A high-speed internet connection", "A secure network protocol", "A group of infected computers controlled by a hacker", "A type of antivirus software"}, 2),
                new Question("What action should you take if you receive an unsolicited request for personal information via email?", new String[]{"Reply with the information requested", "Ignore and delete the email", "Forward the email to friends", "Print the email for record keeping"}, 1),
                new Question("What is the principle of 'least privilege' in cybersecurity?", new String[]{"Giving users only the privileges they need to perform their tasks", "The minimum level of security a system should have", "A guideline for password complexity", "The least amount of effort needed to secure a computer"}, 0)
        )));

        Quiz networkingFundamentalsQuiz = new Quiz("Networking Fundamentals", new LinkedList<>(Arrays.asList(
                new Question("What layer of the OSI model is responsible for end-to-end communication?", new String[]{"Network", "Transport", "Data Link"}, 1),
                new Question("Which protocol is used for transferring web documents?", new String[]{"FTP", "SMTP", "HTTP", "DNS"}, 2),
                new Question("What device is used to connect multiple networks together?", new String[]{"Switch", "Router"}, 1),
                new Question("Which of these is a private IP address?", new String[]{"8.8.8.8", "192.168.1.1", "172.16.0.1"}, 1),
                new Question("What does NAT stand for?", new String[]{"Network Allocation Table", "New Algorithmic Technology", "Network Address Translation"}, 2),
                new Question("Which command is used to view the routing table?", new String[]{"ipconfig", "route", "netstat"}, 1),
                new Question("What is the primary function of a firewall?", new String[]{"To route traffic", "To block unauthorized access", "To provide DNS services"}, 1),
                new Question("Which of these is an advantage of using a VLAN?", new String[]{"Cost savings", "Security", "Improved Performance", "All of the above"}, 3),
                new Question("What does DHCP stand for?", new String[]{"Direct HTTP Control Protocol", "Dynamic Host Configuration Protocol", "Data Handling Control Process"}, 1),
                new Question("Which protocol is used for securely logging into a remote server?", new String[]{"Telnet", "RDP", "SSH"}, 2)
        )));

//        quizzes.add(infrastructureQuiz);
//        quizzes.add(softwareDevQuiz);
        quizzes.add(basicProgrammingQuiz);
        quizzes.add(webDevelopmentQuiz);
        quizzes.add(cyberSecurityQuiz);
        quizzes.add(networkingFundamentalsQuiz);

        return quizzes;
    }
}
