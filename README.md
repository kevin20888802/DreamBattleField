# 幻想競技場
回合制角色大亂鬥  
![alt text](https://github.com/kevin20888802/DreamBattleField/blob/main/readme/img/1.png?raw=true)

# 戰鬥系統
- 回合制  
- 技能系統  
- 從資料庫抽取隨機1~5名敵人  
![alt text](https://github.com/kevin20888802/DreamBattleField/blob/main/readme/img/2.png?raw=true)

# 職業系統、技能系統
- 目前預設職業：符文騎士、遊俠、法師  
- 職業以CharClass物件設計，一個職業一個CharClass新物件即可，方便快速。  
- 技能以一個Class為單位，繼承自CharSkill物件，Override Use函式的部分來決定技能行為。  
![alt text](https://github.com/kevin20888802/DreamBattleField/blob/main/readme/img/3.png?raw=true)

# 多樣的角色選擇
- 可選擇玩家角色及顯示職業  

# 角色編輯器
- 基於XML  
- 可編輯數值  
- 自訂義角色圖示  
![alt text](https://github.com/kevin20888802/DreamBattleField/blob/main/readme/img/4.png?raw=true)
