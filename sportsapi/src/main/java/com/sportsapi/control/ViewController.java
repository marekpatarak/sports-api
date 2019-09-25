package com.sportsapi.control;

import com.sportsapi.auth.registration.EmailExistsException;
import com.sportsapi.auth.registration.UserDto;
import com.sportsapi.entity.*;
import com.sportsapi.repository.CountryRepository;
import com.sportsapi.repository.LeagueRepository;
import com.sportsapi.repository.TeamsRepository;
import com.sportsapi.service.UserService;
import com.sportsapi.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path="/")
public class ViewController {

    @Autowired
    ViewService viewService;

    @Autowired
    UserService userService;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    LeagueRepository leagueRepository;


    @GetMapping(path="/")
    public String getLandingPage() {

        return "landingpage";
    }

    @GetMapping(path="/countries")
    public String getCountries(Model model) {

        List<Country> countries = viewService.getCountries();
        model.addAttribute("countries", countries);

        return "countries";
    }

    @GetMapping(path="/leagues")
    public String getLeagues(Model model) {

        List<League> leagues = viewService.getLeagues();
        model.addAttribute("leagues", leagues);
        return "leagues";
    }

    @GetMapping(path="/leagues/country/{countryid}")
    public String getLeaguesByCountry(@PathVariable("countryid") String countryId, Model model) {

        List<League> leagues = viewService.getLeaguesByCountry(countryId);
        model.addAttribute("leagues", leagues);
        return "leagues";
    }

    @GetMapping(path="/teams")
    public String getTeams(Model model) {

        List<Team> teams = viewService.getTeams();
        model.addAttribute("teams", teams);

        return "teams";
    }

    @GetMapping(path="/teams/{leagueId}")
    public String getTeamsByLeague(@PathVariable("leagueId") String leagueId, Model model) {

        List<Team> teams = viewService.getTeamsByLeague(leagueId);
        model.addAttribute("teams", teams);

        return "teams";
    }

    @GetMapping(path="/teams/country/{countryId}")
    public String getTeamsByCountry(@PathVariable("countryId") String countryId, Model model) {

        List<Team> teams = viewService.getTeamsByCountry(countryId);
        model.addAttribute("teams", teams);

        return "teams";
    }

    @GetMapping(path="/teams/statistics/{leagueId}")
    public String getTeamStatisticsByLeague(@PathVariable("leagueId") String leagueId, Model model) {

        List<Team> teams = viewService.getTeamsByLeague(leagueId);
        Collections.sort(teams);

        model.addAttribute("teams", teams);

        return "teamStatistics";
    }

    @GetMapping(path="/players")
    public String getPlayers(Model model) {

        List<Player> players = viewService.getPlayers();
        Map<String,Country> countryMap = viewService.getCountryMap(players);
        model.addAttribute("players", players);
        model.addAttribute("countryMap", countryMap);

        return "players";
    }

    @GetMapping(path="/players/{teamId}")
    public String getPlayers(@PathVariable("teamId") String teamId, Model model) {

        List<Player> players = viewService.getPlayersByTeam(teamId);
        Map<String,Country> countryMap = viewService.getCountryMap(players);
        model.addAttribute("players", players);
        model.addAttribute("countryMap", countryMap);

        return "players";
    }
    @GetMapping(path="/players/country/{countryId}")
    public String getPlayersByCountry(@PathVariable("countryId") String countryId, Model model) {

        List<Player> players = viewService.getPlayersByCountry(countryId);
        Map<String,Country> countryMap = viewService.getCountryMap(players);

        model.addAttribute("players", players);
        model.addAttribute("countryMap", countryMap);

        return "players";
    }

    @GetMapping(path="/admin")
    public String getAdminPage() {

        return "admin";
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }


    @PostMapping(path = "/user/registration")
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid UserDto accountDto,
             BindingResult result, WebRequest request, Errors errors) {
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            return new ModelAndView("success", "user", accountDto);
        }
    }

    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

}
