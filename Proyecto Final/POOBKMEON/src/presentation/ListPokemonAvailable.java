package presentation;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import domain.Pokemon;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class ListPokemonAvailable extends SelectionPokemon{
    private String backgroundImage = "emerald";
    private ArrayList<String> pokemonsChosenFight;
    private JButton come;
    public ListPokemonAvailable(POOBkemonGUI newPo, Color newColor){
        super(newPo, newColor);
    }

}