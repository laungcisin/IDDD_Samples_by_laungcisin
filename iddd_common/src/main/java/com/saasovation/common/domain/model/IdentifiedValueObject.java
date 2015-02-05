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

package com.saasovation.common.domain.model;

/**
 * 使用数据库实体保存多个值对象（对象集合）。
 * 第二层超类型--值对象专属。
 * 每一个值对象类型都可以方便地获得一个隐藏的委派主键。
 */
public class IdentifiedValueObject extends IdentifiedDomainObject {

    private static final long serialVersionUID = 1L;

    protected IdentifiedValueObject() {
        super();
    }
}
