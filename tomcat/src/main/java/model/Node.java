package model;

import lombok.Data;

import java.util.List;

@Data
public class Node {

    private TestNode testNode;

    private List<TestApi> apiList;
}
