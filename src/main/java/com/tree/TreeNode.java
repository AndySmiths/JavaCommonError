package com.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
/**
 * A tree node data model used for DFS traversal stream generators
 */
public class TreeNode<T> {
    private final T value;
    private final int level;
    private final TreeNode<T> parentNode;

    TreeNode(T value, int level, TreeNode<T> parentNode) {
        this.value = value;
        this.level = level;
        this.parentNode = parentNode;
    }

    /**
     * Value of the tree node
     */
    public T getValue() {
        return value;
    }

    /**
     * Level (depth) of the current node in the tree
     */
    public int getLevel() {
        return level;
    }

    /**
     * Return the direct parent of the current node.
     */
    public TreeNode<T> getParentNode() {
        return parentNode;
    }

    /**
     * Return a stream that contains all parent (ancestor) nodes back to the root, not include the current node.
     */
    public Stream<TreeNode<T>> getParentStream() {
        if (this.getParentNode() == null) {
            return Stream.empty();
        } else {
//            return Stream.iterate(this.getParentNode(), Objects::nonNull, TreeNode::getParentNode);
            return Stream.empty();
        }
    }

    /**
     * Return a stream that contains nodes in the path from the root, including the current node.
     * This method is less performance, if you don't care about top-down order, use {@link #getParentStream()} instead.
     */
    public Stream<TreeNode<T>> getPath() {
        return Stream.concat(
                // parent nodes in reverse order
                StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                getParentStream()
                                        .collect(Collectors.toCollection(LinkedList::new))
                                        .descendingIterator(),
                                Spliterator.ORDERED
                        ),
                        false
                ),
                // followed by the current node
                Stream.of(this)
        );
    }
}