package com.starbus.starbus.Repository;

import com.starbus.starbus.Domain.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, String> {
}
