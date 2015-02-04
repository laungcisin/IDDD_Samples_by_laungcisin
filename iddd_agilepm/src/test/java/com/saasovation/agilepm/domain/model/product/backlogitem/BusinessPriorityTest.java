//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.saasovation.agilepm.domain.model.product.backlogitem;

import java.text.NumberFormat;

import com.saasovation.agilepm.domain.model.DomainTest;

public class BusinessPriorityTest extends DomainTest {

    public BusinessPriorityTest() {
        super();
    }

    public void testCostPercentageCalculation() throws Exception {

        //1.先创建一个BusinessPriority实例
        BusinessPriority businessPriority = new BusinessPriority(new BusinessPriorityRatings(2, 4, 1, 1));

        //2.复制构造函数创建一个与之相等的实例
        BusinessPriority businessPriorityCopy = new BusinessPriority(businessPriority);

        //3.断言测试两者是否相等
        assertEquals(businessPriority, businessPriorityCopy);

        BusinessPriorityTotals totals = new BusinessPriorityTotals(53, 49, 53 + 49, 37, 33);

        float cost = businessPriority.costPercentage(totals);

        assertEquals("2.7", this.oneDecimal().format(cost));

        //验证costPercentage方法是否是无副作用
        assertEquals(businessPriorityCopy, businessPriority);
    }

    public void testPriorityCalculation() throws Exception {

        BusinessPriority businessPriority =
            new BusinessPriority(new BusinessPriorityRatings(2, 4, 1, 1));

        BusinessPriority businessPriorityCopy =
            new BusinessPriority(businessPriority);

        assertEquals(businessPriority, businessPriorityCopy);

        BusinessPriorityTotals totals =
            new BusinessPriorityTotals(53, 49, 53 + 49, 37, 33);

        float calculatedPriority = businessPriority.priority(totals);

        assertEquals("1.03", this.twoDecimals().format(calculatedPriority));

        assertEquals(businessPriorityCopy, businessPriority);
    }

    public void testTotalValueCalculation() throws Exception {

        BusinessPriority businessPriority =
            new BusinessPriority(new BusinessPriorityRatings(2, 4, 1, 1));

        BusinessPriority businessPriorityCopy =
            new BusinessPriority(businessPriority);

        assertEquals(businessPriority, businessPriorityCopy);

        float totalValue = businessPriority.totalValue();

        assertEquals("6.0", this.oneDecimal().format(totalValue));

        assertEquals(businessPriorityCopy, businessPriority);
    }

    public void testValuePercentageCalculation() throws Exception {

        BusinessPriority businessPriority =
            new BusinessPriority(new BusinessPriorityRatings(2, 4, 1, 1));

        BusinessPriority businessPriorityCopy =
            new BusinessPriority(businessPriority);

        assertEquals(businessPriority, businessPriorityCopy);

        BusinessPriorityTotals totals =
            new BusinessPriorityTotals(53, 49, 53 + 49, 37, 33);

        float valuePercentage = businessPriority.valuePercentage(totals);

        assertEquals("5.9", this.oneDecimal().format(valuePercentage));

        assertEquals(businessPriorityCopy, businessPriority);
    }

    private NumberFormat oneDecimal() {
        return this.decimal(1);
    }

    private NumberFormat twoDecimals() {
        return this.decimal(2);
    }

    private NumberFormat decimal(int aNumberOfDecimals) {
        NumberFormat fmt = NumberFormat.getInstance();
        fmt.setMinimumFractionDigits(aNumberOfDecimals);
        fmt.setMaximumFractionDigits(aNumberOfDecimals);
        return fmt;
    }
}
