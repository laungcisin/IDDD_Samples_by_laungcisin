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

package com.saasovation.agilepm.application.product.backlogitem;

import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemCommitted;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemId;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemRepository;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.product.sprint.SprintId;
import com.saasovation.agilepm.domain.model.product.sprint.SprintRepository;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.common.domain.model.DomainEventPublisher;
import com.saasovation.common.domain.model.DomainEventSubscriber;

public class BacklogItemApplicationService {

    private BacklogItemRepository backlogItemRepository;
    private SprintRepository sprintRepository;

    public BacklogItemApplicationService(
            BacklogItemRepository aBacklogItemRepository) {
        super();

        this.backlogItemRepository = aBacklogItemRepository;
    }

    // TODO: APIs for student assignment
    public void commitBacklogItem(TenantId tenantId,
                                  BacklogItemId backlogItemId,
                                  SprintId sprintId) {
        DomainEventSubscriber subscriber =
                new DomainEventSubscriber<BacklogItemCommitted>() {
                    @Override
                    public void handleEvent(BacklogItemCommitted aDomainEvent) {
                        //TODO:这里处理事件。
                    }

                    @Override
                    public Class<BacklogItemCommitted> subscribedToEventType() {
                        return BacklogItemCommitted.class;
                    }
                };

        DomainEventPublisher.instance().subscribe(subscriber);

        BacklogItem backlogItem = this.backlogItemRepository.backlogItemOfId(tenantId, backlogItemId);
        Sprint sprint = this.sprintRepository.sprintOfId(tenantId, sprintId);
        backlogItem.commitTo(sprint);
    }

    public void backlogItemPlaceholderService() {
        this.backlogItemRepository();
    }

    private BacklogItemRepository backlogItemRepository() {
        return this.backlogItemRepository;
    }
}
