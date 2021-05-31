package ua.goit.jdbc.command;

import ua.goit.jdbc.config.DatabaseConnectionManager;
import ua.goit.jdbc.dto.*;
import ua.goit.jdbc.exceptions.DAOException;
import ua.goit.jdbc.view.View;

import java.util.ArrayList;
import java.util.List;

public class Update extends AbstractCommand implements Command {
    private static final String SECTION_MENU = """
            Please, enter the number according to list below
            1 - update customer
            2 - update company
            3 - update project
            4 - update developer
            5 - update skill
            return - go back to main menu
            """;
    private static final String CUSTOMER_SECTION_MENU = """
            Choose info you would like to update from list below
            1 - update customer name
            2 - update customer industry
            3 - add customer companies and projects
            ok - when you are ready
            """;
    private static final String COMPANY_SECTION_MENU = """
            Choose info you would like to update from list below
            1 - update company name
            2 - update company headquarters
            3 - add company customers and projects
            ok - when you are ready
            """;
    private static final String PROJECT_SECTION_MENU = """
            Choose info you would like to update from list below
            1 - update project name
            2 - update project description
            3 - update project cost
            4 - add project customers and companies
            5 - add project developers
            ok - when you are ready
            """;
    private static final String DEVELOPER_SECTION_MENU = """
            Choose info you would like to update from list below
            1 - update developer first name
            2 - update developer last name
            3 - update developer gender
            4 - update developer salary
            5 - add developer projects
            6 - add developer skills
            ok - when you are ready
            """;
    private static final String SKILL_SECTION_MENU = """
            Choose info you would like to update from list below
            1 - update skill branch
            2 - update skill level
            3 - add skill developers
            ok - when you are ready
            """;
    private final View view;

    public Update(View view, DatabaseConnectionManager connectionManager) {
        super(view, connectionManager);
        this.view = view;
    }

    @Override
    public String commandName() {
        return "update";
    }

    @Override
    public void process() {
        boolean running = true;
        while (running) {
            view.write(SECTION_MENU);
            String section = view.read();
            switch (section) {
                case "1" -> updateCustomer();
                case "2" -> updateCompany();
                case "3" -> updateProject();
                case "4" -> updateDeveloper();
                case "5" -> updateSkill();
                case "return" -> running = false;
                default -> view.write("Please, enter the correct command\n");
            }
        }
    }

    private void updateCustomer() {
        Customer toUpdate = null;
        view.write("Which customer you would like to update?");
        while (toUpdate == null) {
            toUpdate = getByID(getCustomerService(), "customer");
        }
        boolean running = true;
        while (running) {
            view.write(CUSTOMER_SECTION_MENU);
            String field = view.read();
            switch (field) {
                case "1" -> {
                    view.write("Write customer new name");
                    toUpdate.setName(view.read());
                }
                case "2" -> {
                    view.write("Write customer new industry");
                    toUpdate.setIndustry(view.read());
                }
                case "3" -> {
                    toUpdate.setCompanies(getCompaniesFromConsole());
                    toUpdate.setProjects(getProjectsFromConsole());
                }
                case "ok" -> running = false;
                default -> view.write("Please, enter the correct command\n");
            }
        }
        try {
            Customer updated = getCustomerService().update(toUpdate);
            view.write("Updated customer\n" + updated + "\n");
        } catch (DAOException ex) {
            view.write(ex.getMessage());
        }
    }

    private void updateCompany() {
        Company toUpdate = null;
        view.write("Which company you would like to update?");
        while (toUpdate == null) {
            toUpdate = getByID(getCompanyService(), "company");
        }
        boolean running = true;
        while (running) {
            view.write(COMPANY_SECTION_MENU);
            String field = view.read();
            switch (field) {
                case "1" -> {
                    view.write("Write company new name");
                    toUpdate.setName(view.read());
                }
                case "2" -> {
                    view.write("Write company new headquarters");
                    toUpdate.setHeadquarters(view.read());
                }
                case "3" -> {
                    toUpdate.setCustomers(getCustomersFromConsole());
                    toUpdate.setProjects(getProjectsFromConsole());
                }
                case "ok" -> running = false;
                default -> view.write("Please, enter the correct command\n");
            }
        }
        try {
            Company updated = getCompanyService().update(toUpdate);
            view.write("Updated company\n" + updated + "\n");
        } catch (DAOException ex) {
            view.write(ex.getMessage());
        }
    }

    private void updateProject() {
        Project toUpdate = null;
        view.write("Which project you would like to update?");
        while (toUpdate == null) {
            toUpdate = getByID(getProjectService(), "project");
        }
        boolean running = true;
        while (running) {
            view.write(PROJECT_SECTION_MENU);
            String field = view.read();
            switch (field) {
                case "1" -> {
                    view.write("Write project new name");
                    toUpdate.setName(view.read());
                }
                case "2" -> {
                    view.write("Write project new description");
                    toUpdate.setDescription(view.read());
                }
                case "3" -> toUpdate.setCost(getDoubleFromConsole("Write project new cost"));
                case "4" -> {
                    toUpdate.setCustomers(getCustomersFromConsole());
                    toUpdate.setCompanies(getCompaniesFromConsole());
                }
                case "5" -> toUpdate.setDevelopers(getDevelopersFromConsole());
                case "ok" -> running = false;
                default -> view.write("Please, enter the correct command\n");
            }
        }
        try {
            Project project = getProjectService().update(toUpdate);
            view.write("Updated project\n" + project + "\n");
        } catch (DAOException ex) {
            view.write(ex.getMessage());
        }
    }

    private void updateDeveloper() {
        Developer toUpdate = null;
        view.write("Which developer you would like to update?");
        while (toUpdate == null) {
            toUpdate = getByID(getDeveloperService(), "developer");
        }
        boolean running = true;
        while (running) {
            view.write(DEVELOPER_SECTION_MENU);
            String field = view.read();
            switch (field) {
                case "1" -> {
                    view.write("Write developer new first name");
                    toUpdate.setFirstName(view.read());
                }
                case "2" -> {
                    view.write("Write developer new last name");
                    toUpdate.setLastName(view.read());
                }
                case "3" -> toUpdate.setSex(getGenderFromConsole());
                case "4" -> toUpdate.setSalary(getDoubleFromConsole("Write developer new salary"));
                case "5" -> toUpdate.setProjects(getProjectsFromConsole());
                case "6" -> toUpdate.setSkills(getSkillsFromConsole());
                case "ok" -> running = false;
                default -> view.write("Please, enter the correct command\n");
            }
        }
        try {
            Developer developer = getDeveloperService().update(toUpdate);
            view.write("Updated developer\n" + developer + "\n");
        } catch (DAOException ex) {
            view.write(ex.getMessage());
        }
    }

    private void updateSkill() {
        Skill toUpdate = null;
        view.write("Which skill you would like to update?");
        while (toUpdate == null) {
            toUpdate = getByID(getSkillService(), "skill");
        }
        boolean running = true;
        while (running) {
            view.write(SKILL_SECTION_MENU);
            String field = view.read();
            switch (field) {
                case "1" -> toUpdate.setBranch(getBranchFromConsole());
                case "2" -> toUpdate.setLevel(getLevelFromConsole());
                case "3" -> toUpdate.setDevelopers(getDevelopersFromConsole());
                case "ok" -> running = false;
                default -> view.write("Please, enter the correct command\n");
            }
        }
        try {
            Skill skill = getSkillService().update(toUpdate);
            view.write("Updated skil;\n" + skill + "\n");
        } catch (DAOException ex) {
            view.write(ex.getMessage());
        }
    }

}
