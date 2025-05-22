package com.locador.api.repository.financial;

import com.locador.api.model.financial.CostCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostCenterRepository extends JpaRepository<CostCenter, Integer> {
}
