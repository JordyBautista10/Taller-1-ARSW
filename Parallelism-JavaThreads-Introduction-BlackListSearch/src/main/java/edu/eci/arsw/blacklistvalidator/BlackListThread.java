package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.LinkedList;

public class BlackListThread extends Thread{

   int BLACK_LIST_ALARM_COUNT=5;
   int n;
   int start;
   int end;
   HostBlacklistsDataSourceFacade skds;
   String ipaddress;
   int ocurrencesCount=0;
   int checkedListsCount=0;
   LinkedList<Integer> blackListOcurrences;

   /**
    *
    * @param start
    * @param end
    * @param n
    * @param BLACK_LIST_ALARM_COUNT
    * @param skds
    * @param ipaddress
    * @param ocurrencesCount
    * @param checkedListsCount
    * @param blackListOcurrences
    */
   public BlackListThread (int start, int end, int n, int BLACK_LIST_ALARM_COUNT, HostBlacklistsDataSourceFacade skds, String ipaddress, int ocurrencesCount, int checkedListsCount, LinkedList<Integer> blackListOcurrences) {
      this.n = n;
      this.start = start;
      this.end = end;
      this.BLACK_LIST_ALARM_COUNT = BLACK_LIST_ALARM_COUNT;
      this.skds = skds;
      this.ipaddress = ipaddress;
      this.ocurrencesCount= ocurrencesCount;
      this.checkedListsCount= checkedListsCount;
      this.blackListOcurrences = blackListOcurrences;
   }

   @Override
   public void run() {

      for (int i=start;i<end && ocurrencesCount<BLACK_LIST_ALARM_COUNT;i++){
         checkedListsCount++;
         if (skds.isInBlackListServer(i, ipaddress)){
            blackListOcurrences.add(i);
            ocurrencesCount++;

         }
      }
   }

   public int getOcurrencesCount () {
      return ocurrencesCount;
   }

   public int getCheckedListsCount(){
      return checkedListsCount;
   }
   public LinkedList<Integer> getBlackListOcurrences() {
      return blackListOcurrences;
   }

}
