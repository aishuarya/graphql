package com.graphqlPractice.graphql.service;

import com.graphqlPractice.graphql.domain.Player;
import com.graphqlPractice.graphql.domain.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    List<Player> players=new ArrayList<>();

    AtomicInteger id =new AtomicInteger(0);

    public List<Player> findAll(){
        return players;
    }

    public Optional<Player> findOne(Integer id){
        return players.stream().filter(player-> Objects.equals(player.id(), id)).findFirst();
    }

    public Player createOnePlayer(String name , Team team ){
        Player player= new Player(id.incrementAndGet(),name,team);
        players.add(player);
        return player;
    }

    public Player deleteOnePlayer(Integer id ){
       Player player= players.stream().filter(ply->ply.id().equals(id)).
                findFirst().
                orElseThrow(()->new IllegalArgumentException("can't find"));
        players.remove(player);
        return player;

    }

    public Player updateOnePlayer(Integer id,String name , Team team  ){
        Player updatePlayer= new Player(id,name,team);
        Optional<Player> current=players.stream().filter(player-> Objects.equals(player.id(), id)).findFirst();
        if(current.isPresent()){
            Player player= current.get();
            int index=players.indexOf(player);
            players.set(index,updatePlayer);
        }
        else {
            throw new IllegalArgumentException("can't do ");
        }
        return updatePlayer;

    }

   @PostConstruct
   private  void init(){
        players.add(new Player(id.incrementAndGet(),"MSD",Team.CSk));
        players.add(new Player(id.incrementAndGet(),"Kohli",Team.RCB));
        players.add(new Player(id.incrementAndGet(),"Pandya",Team.MI));

    }
}
