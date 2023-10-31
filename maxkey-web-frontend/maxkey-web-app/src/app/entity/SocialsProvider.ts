/*
 * Copyright [2022] [MaxKey of copyright http://www.maxkey.top]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { BaseEntity } from './BaseEntity';

export class SocialsProvider extends BaseEntity {
  provider!: String;
  providerName!: String;
  icon!: String;
  clientId!: String;
  clientSecret!: String;
  agentId!: String;
  hidden!: String;
  scanCode!: String;
  switch_hidden: boolean = false;

  constructor() {
    super();
    this.status = 1;
    this.scanCode = 'none';
    this.sortIndex = 1;
  }

  override init(data: any): void {
    Object.assign(this, data);
    if (this.status == 1) {
      this.switch_status = true;
    }
    if (this.hidden == 'true') {
      this.switch_hidden = true;
    }
  }

  override trans(): void {
    if (this.switch_status) {
      this.status = 1;
    } else {
      this.status = 0;
    }

    if (this.switch_hidden) {
      this.hidden = 'true';
    }
  }
}
