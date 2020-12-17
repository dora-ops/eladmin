package model;

import lombok.Data;

import java.util.List;

@Data
public class Graph extends TestGraph{

    private List<Node> tables;

}
