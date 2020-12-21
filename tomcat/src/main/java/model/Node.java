package model;

import lombok.Data;

@Data
public class Node extends TestNode {

    private Node nextPoint;
}
